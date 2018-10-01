/**
 * 
 */
package com.inxedu.os.edu.entity.product;

import java.util.Date;

import lombok.Data;

/**
 * @author 王武
 * @website:www.lemonkt.com
 * @QQ:834667820
 */
@Data
public class Product {
  private String name;//商品名称
  private String des;//商品详情
  private String price;//商品价格
  private String code;//商品编号
  private Date createTime;//创建时间
  private Date updateTime;//更新时间
}
