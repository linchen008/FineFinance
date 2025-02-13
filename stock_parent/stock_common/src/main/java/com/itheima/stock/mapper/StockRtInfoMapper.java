package com.itheima.stock.mapper;

import com.itheima.stock.pojo.domain.StockUpdownDomain;
import com.itheima.stock.pojo.entity.StockRtInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Tommy
 * @description 针对表【stock_rt_info(个股详情信息表)】的数据库操作Mapper
 * @createDate 2025-02-01 22:42:29
 * @Entity com.itheima.stock.pojo.entity.StockRtInfo
 */
public interface StockRtInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockRtInfo record);

    int insertSelective(StockRtInfo record);

    StockRtInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockRtInfo record);

    int updateByPrimaryKey(StockRtInfo record);

    /**
     * 获取最新的股票信息
     *
     * @param curDate
     * @return List<StockUpdownDomain>
     */
    List<StockUpdownDomain> getNewestStockInfo(@Param("timePoint") Date curDate);

    /**
     * 获取涨幅股票信息
     *
     * @param curDate
     * @return List<StockUpdownDomain>
     */
    List<StockUpdownDomain> getIncreaseStockInfo(@Param("timePoint") Date curDate);

    /**
     * 获取跌幅股票信息
     *
     * @param curDate
     * @return List<StockUpdownDomain>
     */
    List<Map> getStockUpdownCount(@Param("openTime") Date openTime, @Param("curTime") Date curTime, @Param("flag") int flag);
}
