package com.gedoumi.crowd.dataobj.form;

import lombok.Data;

/**
 * 分页表单
 *
 * @author Minced
 */
@Data
public class PaginationForm {

    /**
     * 当前页码
     */
    private Integer page = 1;

    /**
     * 每页数据量
     */
    private Integer rows = 30;

}
