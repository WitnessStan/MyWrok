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
                                商品类型ID
                            </th>
                            <th>
                                类型名称
                            </th>
                            <th>
                                类型编号
                            </th>
                            <th>
                                创建时间
                            </th>
                            <th>
                                修改时间
                            </th>
                            <th colspan="2">
                                操作
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                    <#list productCategoryList as productCategory>
                    <tr>
                        <td>
                            ${productCategory.categoryId}
                        </td>
                        <td>
                            ${productCategory.categoryName}
                        </td>
                        <td>
                            ${productCategory.categoryType}
                        </td>
                        <td>
                            ${productCategory.createTime}
                        </td>
                        <td>
                            ${productCategory.updateTime}
                        </td>
                        <td>
                            <a href="/sell/seller/category/index?categoryId=${productCategory.categoryId}">修改</a>
                        </td>
                    </tr>
                    </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>