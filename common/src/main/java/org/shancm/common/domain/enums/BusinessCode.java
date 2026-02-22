package org.shancm.common.domain.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author shancm
 */

@Getter
@AllArgsConstructor
public enum BusinessCode {
    SUCCESS("0000", "成功");


    private final String code;
    private final String msg;
}
