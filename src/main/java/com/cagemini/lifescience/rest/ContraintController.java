package com.cagemini.lifescience.rest;

import com.cagemini.lifescience.entity.Contraint;
import com.cagemini.lifescience.model.ApiResponse;
import com.cagemini.lifescience.model.ContraintInfo;
import com.cagemini.lifescience.service.ContraintSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContraintController {
    private ContraintSevice contraintSevice;

    @Autowired
    public ContraintController(ContraintSevice contraintSevice) {
        this.contraintSevice = contraintSevice;
    }
    @PostMapping("/contraint")
    public ApiResponse addContraint(@RequestParam("coursId") Long coursId, @RequestBody Contraint theContraint){
        contraintSevice.save(coursId, theContraint);
        return (new ApiResponse("Contraint added!"));
    }
    @GetMapping("/contraint")
    public ContraintInfo getContraintByCoursId(@RequestParam("coursId") Long coursId){
        return contraintSevice.getContraintByCoursID(coursId);
    }

    @PutMapping("/contraint")
    public ApiResponse updateContraint(@RequestParam("contraintId") Long contraintId, @RequestParam("coursId") Long coursId, @RequestBody Contraint theContraint){
        contraintSevice.updateContraint(contraintId, coursId, theContraint);
        return (new ApiResponse("Contraint updated!"));
    }
    @DeleteMapping("/contraint")
    public ApiResponse deleteContraint(@RequestParam("contraintId") Long contraintId, @RequestParam("coursId") Long coursId) {
        contraintSevice.deleteContraint(contraintId, coursId);
        return (new ApiResponse("Contraint Deleted!"));
    }
}
