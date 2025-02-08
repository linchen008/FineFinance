package com.itheima.stock.mapper;

import com.itheima.stock.pojo.domain.InnerMarketDomain;
import com.itheima.stock.pojo.entity.StockMarketIndexInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author Tommy
 * @description 针对表【stock_market_index_info(国内大盘数据详情表)】的数据库操作Mapper
 * @createDate 2025-02-01 22:42:29
 * @Entity com.itheima.stock.pojo.entity.StockMarketIndexInfo
 */
public interface StockMarketIndexInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockMarketIndexInfo record);

    int insertSelective(StockMarketIndexInfo record);

    StockMarketIndexInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockMarketIndexInfo record);

    int updateByPrimaryKey(StockMarketIndexInfo record);

    /**
     * Get all real-time inner market data
     *
     * @param inners   inner stock id
     * @param timePoint last transaction date
     * @return List<InnerMarketDomain>
     */
    List<InnerMarketDomain> getMarketInfo(
            @Param("marketIds") List<String> inners,
            @Param("timePoint") Date timePoint);
}
