<html>
    <#include "../common/header.ftl">
    <body>
    <div id="wrapper" class="toggled">
        <#include "../common/nav.ftl">
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>
                                    订单ID
                                </th>
                                <th>
                                    姓名
                                </th>
                                <th>
                                    手机号
                                </th>
                                <th>
                                    地址
                                </th>
                                <th>
                                    总金额
                                </th>
                                <th>
                                    订单状态
                                </th>
                                <th>
                                    支付状态
                                </th>
                                <th>
                                    创建时间
                                </th>
                                <th colspan="2">
                                    操作
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                    <#list orderDTOPage.getContent() as orderDTO>
                    <tr>
                        <td>
                            ${orderDTO.orderId}
                        </td>
                        <td>
                            ${orderDTO.buyerName}
                        </td>
                        <td>
                            ${orderDTO.buyerPhone}
                        </td>
                        <td>
                            ${orderDTO.buyerAddress}
                        </td>
                        <td>
                            ${orderDTO.orderAmount}
                        </td>
                        <td>
                            ${orderDTO.getOrderStatusEnum().message}
                        </td>
                        <td>
                            ${orderDTO.getPayStatusEnum().message}
                        </td>
                        <td>
                            ${orderDTO.createTime}
                        </td>
                        <td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                            <#if orderDTO.getOrderStatusEnum().message == "新订单">
                                <td><a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}" onclick="return confirm('确定要取消？')">取消订单</a></td>
                            </#if>
                    </tr>
                    </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <ul class="pagination">
                    <#if currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="?currentPage=${currentPage - 1}&pageSize=${pageSize}">上一页</a></li>
                    </#if>
                    <#list 1..orderDTOPage.getTotalPages() as index>
                        <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="?currentPage=${index}&pageSize=${pageSize}">${index}</a></li>
                        </#if>
                    </#list>
                    <#if currentPage gte orderDTOPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="?currentPage=${currentPage + 1}&pageSize=${pageSize}">下一页</a></li>
                    </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</html>