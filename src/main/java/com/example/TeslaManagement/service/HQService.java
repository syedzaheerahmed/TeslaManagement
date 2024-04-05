package com.example.TeslaManagement.service;

import com.example.TeslaManagement.model.HQ;
import java.util.List;

public interface HQService {
    String createHq(HQ hq);
    String updateHq(HQ hq);
    String deleteHq(Long hq_id);
    HQ getHqDetails(Long hq_id);
    List<HQ> getAllHq();
}
