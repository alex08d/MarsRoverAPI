package com.SpringProject.Service;
import com.SpringProject.Dto.HomeDto;
import com.SpringProject.Repository.PreferencesRepo;
import com.SpringProject.Response.MarsPhotos;
import com.SpringProject.Response.MarsRoverApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


@Service
public class MarsRoverApiService {
    @Autowired
    private PreferencesRepo preferencesRepo;

    private static final String API_KEY = "D2G8e45Uzz8kkSh18AHusbIV7ckMrPX94oRdrbNx";
    ResponseEntity<MarsRoverApiResponse> response = null;
    public Map<String,List<String>> validCameras = new HashMap<>();
    public MarsRoverApiService() {
        validCameras.put("Opportunity", Arrays.asList("FHAZ","RHAZ","NAVCAM","PANCAM","MINITES"));
        validCameras.put("Curiosity",Arrays.asList("FHAZ","RHAZ","MAST","CHEMCAM","MAHLI","MARDI","NAVCAM"));
        validCameras.put("Spirit",Arrays.asList("FHAZ","RHAZ","NAVCAM","PANCAM","MINITES"));
    }
    public MarsRoverApiResponse getRoverData(HomeDto homeDto) throws InvocationTargetException, IllegalAccessException {
        RestTemplate rt = new RestTemplate();
        List<String> apiUrlEndpoints = getApiUrlsEndpoints(homeDto);
        List<MarsPhotos> photos = new ArrayList<>();
        MarsRoverApiResponse response = new MarsRoverApiResponse();
        apiUrlEndpoints.stream()
                .forEach(url-> {
                    MarsRoverApiResponse apiResponse = rt.getForObject(url, MarsRoverApiResponse.class);
                    photos.addAll(apiResponse.getPhotos());
                });
        response.setPhotos(photos);
        return response;
    }
    public List<String> getApiUrlsEndpoints(HomeDto homeDto) throws InvocationTargetException, IllegalAccessException {
        List<String> urls = new ArrayList<>();
        Method [] methods = homeDto.getClass().getMethods();

        //This method will grab all getCamera* methods and (if value returns true) then we will build an API URL to call
        // in order to fetch pictures for a given rover / camera / sol
        for(Method method: methods) {
            if(method.getName().contains("isCamera") && Boolean.TRUE.equals(method.invoke(homeDto))) {
              String cameraName =  method.getName().split("isCamera")[1].toUpperCase();
              if(validCameras.get(homeDto.getMarsApiRoverData()).contains(cameraName)) {
                  urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY + "&camera="+cameraName);
              }
            }
        }
        return  urls;
    }

    public Map<String, List<String>> getValidCameras() {
        return validCameras;
    }

    public HomeDto save(HomeDto homeDto) {
        return preferencesRepo.save(homeDto);
    }

    public HomeDto findByUserId(Long userId) {
        return preferencesRepo.findByUserId(userId);
    }
}

