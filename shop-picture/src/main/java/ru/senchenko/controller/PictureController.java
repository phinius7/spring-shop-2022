package ru.senchenko.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.senchenko.service.PictureService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/picture")
public class PictureController {

    private static final Logger logger = LoggerFactory.getLogger(PictureController.class);

    private final PictureService pictureService;

    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/{pictureId}")
    public void downloadProductPicture(@PathVariable("pictureId") Integer id, HttpServletResponse response) throws IOException {
        logger.info("Start download picture with id: {}", id);
        Optional<String> opt = pictureService.getPictureContentTypeById(id);
        if (opt.isPresent()) {
            response.setContentType(opt.get());
            Optional<byte[]> pic = pictureService.getPictureDataById(id);
            if (pic.isPresent()) {
                response.getOutputStream().write(pic.get());
            } else {
                logger.error("Empty picture data. Id: {}", id);
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    @GetMapping("/{pictureId}/delete")
    public void deleteById(@PathVariable("pictureId") Integer id,
                           HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        pictureService.deleteById(id);
        response.sendRedirect(request.getHeader("referer"));
    }
}
