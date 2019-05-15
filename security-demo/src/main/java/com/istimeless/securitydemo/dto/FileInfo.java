package com.istimeless.securitydemo.dto;

import lombok.*;

/**
 * @author lijiayin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FileInfo {
    private String path;
}
