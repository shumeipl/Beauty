package com.pengling.beauty.controller;

import com.alibaba.fastjson.JSONArray;
import com.pengling.beauty.entity.Composition;
import com.pengling.beauty.entity.CompositionPro;
import com.pengling.beauty.service.CompositionService;
import com.pengling.beauty.service.GoodService;
import com.pengling.beauty.service.OcrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OcrController {
    @Autowired
    private CompositionService compositionService;
    @Autowired
    private OcrService ocrService;
    @Autowired
    private GoodService goodService;
    @RequestMapping ( "POST/getCompositionTable")
    @ResponseBody
    public String Ocr(HttpServletRequest request){
        String goodId = request.getParameter("goodId");
        System.out.println(goodId);
        String path = goodService.getMaterial(Integer.valueOf(goodId)).get(0);
        List<String> names =  ocrService.ocr(path);
        List<Composition> compositions = new ArrayList<>();
        for(String name:names){
            System.out.println(name);
            Composition composition = compositionService.queryCompositionInfo(name).get(0);
            compositions.add(composition);
        }
        System.out.println(compositions);
        List<String> zdfx = new ArrayList<>();
        List<String> yfsy = new ArrayList<>();
        List<String> fxcf = new ArrayList<>();
        List<String> xj = new ArrayList<>();
        List<String> ffj = new ArrayList<>();
        List<String> jj = new ArrayList<>();
        for (Composition composition : compositions)
        {
            if (composition.getFFJ()!=null&&composition.getFFJ().equals("1")){
                ffj.add(composition.getCompositionName());
            }
            if(composition.getYFCannot()!=null&&composition.getYFCannot().equals("1")){
                yfsy.add(composition.getCompositionName());
            }
            if (composition.getFX()!=null&&composition.getFX().equals("1")){
                fxcf.add(composition.getCompositionName());
            }
            if (composition.getXJ()!=null&&composition.getXJ().equals("1")){
                xj.add(composition.getCompositionName());
            }
            if (composition.getJJ()!=null&&composition.getJJ().equals("1")){
                jj.add(composition.getCompositionName());
            }
            if (composition.getZDCF()!=null&&composition.getZDCF().equals("1")){
                zdfx.add(composition.getCompositionName());
            }
        }
        CompositionPro compositionPro = new CompositionPro();
        compositionPro.setCompositionList(compositions);
        compositionPro.setXj(xj);
        compositionPro.setJj(jj);
        compositionPro.setFfj(ffj);
        compositionPro.setFxfg(fxcf);
        compositionPro.setYssy(yfsy);
        compositionPro.setZdfx(zdfx);
        return JSONArray.toJSONString(compositionPro);
    }
}
