package uow.itpm.teamblue.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uow.itpm.teamblue.model.SubmitResponse;
import uow.itpm.teamblue.model.UploadFileResponse;
import uow.itpm.teamblue.model.User;
import uow.itpm.teamblue.model.repo.UserRepository;
import uow.itpm.teamblue.services.FileStorageService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/uploadFile")
    public SubmitResponse uploadFile(HttpServletRequest request, @RequestParam("file")MultipartFile file,
                                         @RequestParam("language")String language){
        User user = (User) request.getSession().getAttribute("user");
        user = userRepository.findByUsername(user.getUsername());

        SubmitResponse submitResponse = fileStorageService.storeFile(file, user, language);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(submitResponse.getDocument().getDocumentName())
                .toUriString();

//        return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
        return submitResponse;
    }

    @PostMapping("/uploadMultipleFiles")
    public List<SubmitResponse> uploadMultipleFiles(HttpServletRequest request,
                                                        @RequestParam("files") MultipartFile[] files,
                                                        @RequestParam("language")String language){
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(request, file, language))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        logger.debug("Get file " + fileName);
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
