package com.op.gg.ogj.config.exception.domain;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessage {

    private String status;
    private String msg;

}
