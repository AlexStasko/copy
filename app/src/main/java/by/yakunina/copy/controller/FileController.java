package by.yakunina.copy.controller;

import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.service.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.stream.Collectors;

@Controller
public class FileController {

    @javax.annotation.Resource
    private StorageService storageService;

    @GetMapping("/files")
    public String listUploadedFiles(Model model) {

        model.addAttribute("files", storageService.loadAll().stream().map(
                name -> MvcUriComponentsBuilder.fromMethodName(FileController.class,
                        "serveFile", name).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("orderId") String orderId,
                                   RedirectAttributes redirectAttributes) {


        EntityId fileId = storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        redirectAttributes.addFlashAttribute("filename", file.getOriginalFilename());
        redirectAttributes.addFlashAttribute("orderId", orderId);
        redirectAttributes.addFlashAttribute("fileId", fileId.getId());

        return "redirect:/service";
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(FileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
