package com.example.TeslaManagement.service;

import com.example.TeslaManagement.model.HQ;
import java.util.List;

public interface HQService {
    public String createHq(HQ hq);
    public String updateHq(HQ hq);
    public String deleteHq(Long hq_id);
    public HQ getHqDetails(Long hq_id);
    public List<HQ> getAllHq();
}
