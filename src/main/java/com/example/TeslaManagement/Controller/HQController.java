package com.example.TeslaManagement.Controller;

import com.example.TeslaManagement.model.HQ;
import com.example.TeslaManagement.service.HQService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/HQ")
public class HQController {
    HQService hqService;
    public HQController(HQService hqService) {
        this.hqService = hqService;
    }

    //Get the given HQ
    @GetMapping("/getHQDetails/{hq_id}")
    public HQ getHQDetails(@PathVariable(value = "hq_id") Long hq_id) {
        return hqService.getHqDetails(hq_id);
    }

    //Get all HQ
    @GetMapping("/getAllHQDetails")
    public List<HQ> getAllHQDetails() {
        return hqService.getAllHq();
    }

    //Add HQ
    @PostMapping("/addHQDetails")
    public String addHQDetails(@RequestBody HQ hq) {
        return hqService.createHq(hq);
    }

    //update HQ
    @PutMapping("/updateHQDetails")
    public String updateHQDetails(@RequestBody HQ hq) {
        return hqService.updateHq(hq);
    }

    //delete HQ
    @DeleteMapping("/deleteHQDetails/{hq_id}")
    public String deleteHQDetails(@PathVariable(value = "hq_id") Long hq_id) {
        return hqService.deleteHq(hq_id);
    }
}
