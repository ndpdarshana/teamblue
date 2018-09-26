package uow.itpm.teamblue.services;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uow.itpm.teamblue.exceptions.FileNotFoundException;
import uow.itpm.teamblue.exceptions.FileStorageException;
import uow.itpm.teamblue.model.*;
import uow.itpm.teamblue.model.repo.DocumentRepository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileStorageService {
    private final Path fileStorageLocation;

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private RequestHandlerService requestHandlerService;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties){
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

        try{
            Files.createDirectories(this.fileStorageLocation);
        }catch(Exception e){
            throw new FileStorageException("Could not create the directory for file storage.", e);
        }
    }

    public SubmitResponse storeFile(MultipartFile file, User user, String language){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try{
            if(fileName.contains("..")){
                throw new FileStorageException(("Invalid filename. Please check your file and try again"));
            }

            String ext = fileName.split("\\.")[1];
            if(ext.equals("txt")){
                Path targetLocation = this.fileStorageLocation.resolve(fileName);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                TextInputRequest textInputRequest = new TextInputRequest();
                textInputRequest.setText(IOUtils.toString(file.getInputStream(), Charset.defaultCharset()));
                List<String> languageList = new ArrayList<>();
                languageList.add(language);
                textInputRequest.setLanguageList(languageList);
                textInputRequest.setFile(true);
                textInputRequest.setFileName(fileName);

                return requestHandlerService.textInputHandler(textInputRequest, user);
            }else{
                throw new FileStorageException("Only .txt files accepted");
            }
        }catch(IOException e){
            throw new FileStorageException("Could not store file. Please try again", e);
        }
    }

    public Resource loadFileAsResource(String fileName){
        try{
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()){
                return resource;
            }else{
                throw new FileNotFoundException("File not found");
            }
        }catch(MalformedURLException e){
            throw new FileNotFoundException("File not found", e);
        }
    }
}
