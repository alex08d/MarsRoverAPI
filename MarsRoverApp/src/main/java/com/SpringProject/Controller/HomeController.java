package com.SpringProject.Controller;
import com.SpringProject.Dto.HomeDto;
import com.SpringProject.Response.MarsRoverApiResponse;
import com.SpringProject.Service.MarsRoverApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.lang.reflect.InvocationTargetException;
@Controller
public class HomeController {

    @Autowired
    private MarsRoverApiService roverService;

    @GetMapping("/")
    public String getHomeView (ModelMap model, HomeDto homeDto, Long userId, Boolean createUser) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        homeDto = createDefaultHomeDto(userId);
        if (Boolean.TRUE.equals(createUser) && userId == null) {
            homeDto = roverService.save(homeDto);
        } else {
            homeDto = roverService.findByUserId(userId);
            if (homeDto == null) {
                homeDto = createDefaultHomeDto(userId);
            }
        }

        MarsRoverApiResponse roverData = roverService.getRoverData(homeDto);
        model.put("roverData", roverData);
        model.put("homeDto", homeDto);
        model.put("validCameras", roverService.getValidCameras().get(homeDto.getMarsApiRoverData()));

        if (!Boolean.TRUE.equals(homeDto.isRememberPreferences()) && userId != null) {
            homeDto = createDefaultHomeDto(userId);
            roverService.save(homeDto);
        }

        return "index";
    }

    @GetMapping("/savedPreferences")
    @ResponseBody
    public HomeDto getSavedPreferences (Long userId) {
        if (userId != null)
            return roverService.findByUserId(userId);
        else
            return createDefaultHomeDto(userId);
    }

    private HomeDto createDefaultHomeDto(Long userId) {

        HomeDto homeDto = new HomeDto();
        homeDto.setMarsApiRoverData("Curiosity");
        homeDto.setMarsSol(1);
        homeDto.setUserId(userId);
        return homeDto;
    }

    @PostMapping("/")
    public String postHomeView (HomeDto homeDto) {
        homeDto = roverService.save(homeDto);
        return "redirect:/?userId="+homeDto.getUserId();
    }

}