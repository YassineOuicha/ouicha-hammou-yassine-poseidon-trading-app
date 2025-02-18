package com.nnk.springboot.services;

import com.nnk.springboot.controllers.CurveController;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurvePointService {

    @Autowired
    private CurvePointRepository curvePointRepository;


    public List<CurvePoint> getAllCurvePoint(){
        return curvePointRepository.findAll();
    }


}
