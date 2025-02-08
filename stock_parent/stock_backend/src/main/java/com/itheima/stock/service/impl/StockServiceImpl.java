package com.itheima.stock.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.itheima.stock.mapper.StockBlockRtInfoMapper;
import com.itheima.stock.mapper.StockMarketIndexInfoMapper;
import com.itheima.stock.pojo.domain.InnerMarketDomain;
import com.itheima.stock.pojo.domain.StockBlockDomain;
import com.itheima.stock.pojo.vo.StockInfoConfig;
import com.itheima.stock.service.StockService;
import com.itheima.stock.utils.DateTimeUtil;
import com.itheima.stock.vo.resp.R;
import com.itheima.stock.vo.resp.ResponseCode;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 08/02/2025 17:31
 * @Description :
 */
@Service("stockService")
public class StockServiceImpl implements StockService {
    @Autowired
    private StockInfoConfig stockInfoConfig;

    @Autowired
    private StockMarketIndexInfoMapper stockMarketIndexInfoMapper;

    @Autowired
    private StockBlockRtInfoMapper stockBlockRtInfoMapper;

    /**
     * Get all real-time inner market data
     *
     * @return R<List < InnerMarketDomain>>
     */
    @Override
    public R<List<InnerMarketDomain>> getInnerIndexAll() {
        //TODO: 1. get all inner stock id
        List<String> inners = stockInfoConfig.getInner();

//        //TODO: 2. get latest stock transaction data
//        Date lastDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
//
//        //TODO: 3. mock data for testing
        DateTimeFormatter formatter =
                DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
                        .withZone(DateTimeZone.forID("Asia/Shanghai"));
        Date lastDate = DateTime.parse("2022-01-02 09:30:00", formatter).toDate();

        // 验证时间格式（调试用）
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        System.out.println("DEBUG lastDate: " + sdf.format(lastDate));
        // 应输出 2022-01-02 09:30:00

        //TODO: get data from database
        List<InnerMarketDomain> list = stockMarketIndexInfoMapper.getMarketInfo(inners, lastDate);

        //TODO: return data
        return R.ok(list);
    }

    /**
     * Get all sector data
     *
     * @return R<List < StockBlockDomain>>
     */
    @Override
    public R<List<StockBlockDomain>> sectorAllLimit() {
        //TODO: 1. get latest stock transaction date
        Date lastDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();

        //mock date for testing
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(DateTimeZone.forID("Asia/Shanghai"));
        lastDate = DateTime.parse("2021-12-21 14:30:00", formatter).toDate();

        //TODO: 2. get all sector data and assemble them
        List<StockBlockDomain> infos = stockBlockRtInfoMapper.sectorAllLimit(lastDate);

        if (CollectionUtil.isEmpty(infos)) {
            return R.error(ResponseCode.NO_RESPONSE_DATA.getMessage());
        }

        //TODO: 3. return data
        return R.ok(infos);
    }
}
