package com.itheima.stock.vo.resp;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 */
@Data
@Schema(name = "PageResult", description = "分页工具类")
public class PageResult<T> implements Serializable {
    /**
     * 总记录数
     */
    @Schema(description = "总记录数")
    private Long totalRows;

    /**
     * 总页数
     */
    @Schema(description = "总页数")
    private Integer totalPages;

    /**
     * 当前第几页
     */
    @Schema(description = "当前第几页")
    private Integer pageNum;
    /**
     * 每页记录数
     */
    @Schema(description = "每页记录数")
    private Integer pageSize;
    /**
     * 当前页记录数
     */
    @Schema(description = "当前页记录数")
    private Integer size;
    /**
     * 结果集
     */
    @Schema(description = "结果集")
    private List<T> rows;

    /**
     * 分页数据组装
     * @param pageInfo 分页信息
     * @return
     */
    public PageResult(PageInfo<T> pageInfo) {
        totalRows = pageInfo.getTotal();
        totalPages = pageInfo.getPages();
        pageNum = pageInfo.getPageNum();
        pageSize = pageInfo.getPageSize();
        size = pageInfo.getSize();
        rows = pageInfo.getList();
    }
}