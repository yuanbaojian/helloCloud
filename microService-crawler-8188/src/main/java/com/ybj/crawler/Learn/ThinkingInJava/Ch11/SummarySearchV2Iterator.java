//package com.ybj.crawler.Learn.ThinkingInJava.Ch11;
//
//import com.alibaba.nacos.common.utils.CollectionUtils;
//import com.yy.paymentacceptance.paymentcenterdata.web.pojo.api.es2.EsRequest;
//import com.yy.paymentacceptance.paymentcenterdata.web.pojo.api.es2.EsResponse;
//import com.yy.paymentacceptance.paymentcenterdata.web.pojo.api.response.vo.TransactionOrdersResp;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
///**
// * @author lp211200227
// */
//@Slf4j
//@Setter
//@Getter
//public class SummarySearchV2Iterator<T extends EsRequest,R extends EsResponse<TransactionOrdersResp>> implements Iterator<R> {
//
//
//    private Function<T, R> function;
//    private T request;
//    private R result;
//
//    /**
//     * 请求ES循环次数
//     */
//    private int count;
//
//    public SummarySearchV2Iterator(Function<T, R> function, T request) {
//        this.function = function;
//        this.request = request;
//    }
//
//    @Override
//    public boolean hasNext() {
//        // 已请求并且已没有下一页
//        if (result != null && CollectionUtils.isEmpty(result.getData().getTransactionQuery().getDataList())) {
//            return false;
//        }
//        // 请求下一页
//        result = function.apply(request);
//        // 有数据
//        return result != null && CollectionUtils.isNotEmpty(result.getData().getTransactionQuery().getDataList());
//    }
//
//    @Override
//    public R next() {
//        count++;
//        //翻页
//        List dataList = result.getData().getTransactionQuery().getDataList();
//        TransactionOrdersResp resp = (TransactionOrdersResp)dataList.get(dataList.size() - 1);
//        String tradeOrderId = resp.getTradeOrderId();
//        Long created = resp.getCreated();
//        log.info("SummarySearchV2Iterator last tradeOrderId:{}", tradeOrderId);
//        request.setRequests(request.getRequests().stream().map(dataReq -> {
//            dataReq.setOffset(dataReq.getOffset() + dataReq.getSize());
//            // 使用searchAfter进行深度翻页 突破总数1w限制
//            dataReq.setSearchAfter(new String[]{created.toString(), tradeOrderId});
//            return dataReq;
//        }).collect(Collectors.toList()));
//        return result;
//    }
//
//}
