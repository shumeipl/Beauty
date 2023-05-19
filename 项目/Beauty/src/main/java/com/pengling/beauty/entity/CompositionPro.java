package com.pengling.beauty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
public class CompositionPro {
    private List<Composition> compositionList;
    private List<String> ffj;
    private List<String> zdfx;
    private List<String> fxfg;
    private List<String> xj;
    private List<String> jj;
    private List<String> yssy;
}
