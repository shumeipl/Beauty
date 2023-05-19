package com.pengling.beauty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
public class Composition {
    private Integer compositionId;
    private String compositionName;
    private String compositionSecurityLow;
    private String compositionSecurityHigh;
    private String compositionActivity;
    private String compositionCauseAcne;
    private String compositionAim;
    private String YFCannot;
    private String JJ;
    private String FFJ;
    private String ZDCF;
    private String XJ;
    private String FX;
}
