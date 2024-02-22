package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.model.HQ;
import com.example.TeslaManagement.repository.HQRepo;
import com.example.TeslaManagement.service.HQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HQServiceImpl implements HQService {
    @Autowired
    HQRepo hqRepo;
    @Override
    public String createHq(HQ hq) {
        try{
            hqRepo.save(hq);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
            return "Error in adding HQ details";
        }
        return "HQ details added successfully";
    }

    @Override
    public String updateHq(HQ hq) {
        try{
            hqRepo.save(hq);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
            return "Error in updating hq details";
        }
        return "HQ details updated successfully";
    }

    @Override
    public String deleteHq(Long hq_id) {
        try{
            hqRepo.deleteById(hq_id);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
            return "Error in deleting hq details";
        }
        return "HQ details deleted successfully";
    }

    @Override
    public HQ getHqDetails(Long hq_id) {
        try {
            if(hqRepo.findById(hq_id).isPresent()) {
                return hqRepo.getReferenceById(hq_id);
            }
        }catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
        }
        return null;
    }

    @Override
    public List<HQ> getAllHq() {
        return hqRepo.findAll();
    }
}
