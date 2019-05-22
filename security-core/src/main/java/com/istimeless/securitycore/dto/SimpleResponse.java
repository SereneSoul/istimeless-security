package com.istimeless.securitycore.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @author lijiayin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleResponse implements Serializable {
    
    private Object content;
}
