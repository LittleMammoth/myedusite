package com.inxedu.os.common.constants;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016091100483389";

	// 商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCQHKzTwYVAZnu0oBjmQLDMmjjmB2dfN36Oard/EHZjUUDz5tMoSGo1UZ8Nwa7QH6wZ/QyiqiQKDJqJ+oNXwzYoXzLnK99we66HJFFG2JfB/g3wOMrVVwINOfkUzIf4Et1Fl8JtUs4It5/xOIOCh/vj6/Kap0m7PCgpfge97JFn+JgaURcZD3w4J9lZFfbM/JqfB7Hru2HdQoHo+iH296KaLD6mQvTvI1sQUOUF7i/LMvx+Ss2ODHxiA6b6vaY22/gHi803RRfWO+WCKeKhqccq4HAjjOtrU2RnKy5SV1bXWhNcYX1wYRGKKF2HuMbsfk3OibIEA7ixyZ8xRdYcB/bRAgMBAAECggEACCh3aC1mW7Txf9IoVrCG0a5RVkRJB8zebzV/WcQWz5yD7sq8XWVsDcSnGmJCgSmp1nbKxlKXY7I+9VynHsrAQBzTht6m8Xku8huONlKAlMzLATRV1ynrxc/oV9MUR2jZ+dyTdxje7QNoHIG0gCkui0KKI1MhC2ZmZ6Lo0UmZGaeUw3ayeHnLXAt6DkPMwNUNpeRZ4EDfIw0KfPk03fHikrWJTFc2OV3K4vc4yPVIhcGuRP/Rag/eZZdAT5dayQ9SDp4qeCWKFvaN4ak1crBJ8nmOgpN9641OUM0c8epgBcEWcqy2XRcU/hIGELFUFOMxiRc0Nfqbpz/mU9lWHe4HIQKBgQDuVCEXlfBD+HNzFZ9LNKJp9AC0FvBUw1C+5fXOs+Jzr8iLi5EfwVEeowY9Xt7gzl6jWMtC0yu5ecBcfLDakswHdZX7AgTjvHvv4mCRgTkmPP81emAjsHDswg8qQuYTh2FEa/bbQO29cB/czy1V/nYSLu4QMYTLxerRG2C5MLZnJQKBgQCazCiwN1a1kZ9HF5Z6spYwdv/UzUoO5pgXDlq6Rcp8klWfYMQmAp/YvBXQ512X67PqR/5XH88z+JJf9o1ApA4RjsVOZCwJ1hUwQvi22AFaN782PEnddnzEOwXQg2nz80svAdz35nvhEE1/A+/+XtUkR+xp2TOZk3CRYWg4btPnPQKBgQDMXP2nYN4PGsu8oozeizq9ygqUYpsgw/7IQx59acjD1LcN2dbOuREbSUW/eEt7z9pdDXX2TmfO+rTVWeK4+mnT96NtArU4p1ZLQ/30py9fN6zLqa0ozVJ34ovofEcX9d2VM1MRfWKfL7dSu6Cvfay3OODQp/WsWoFZX7WX90phtQKBgFlVKsIPHZYpFM9VOH1UpMIRWrC3TF7vCqR7nzZSYnmjhZA1qtz4tMDCQqDNdMDgwHKa25fx/oTSlb6VrYy5YHkXeJw1yQhgKNgyPhBe9eDXvRySjFonL/eazE86F6I2LJRTMe9KbwFChEahwhV9KuoGK9QmCdY+NT9hBGFU7CtNAoGBAIWY+UmiLojgx+7T9B4NtoA5SM/PFK7FjpCmIfaCjqX7AzNw5OyblkvDjGw10+UNXO3tRC0zCY8tLFMG6r0nwpW0hryduqIDKbq5EQ1vcT/bdw6qa1PB5Yo/mvTsMPLU7EGxXWZpiWKRrKKzcTs3nFt0DauDeYwPb0e0pDwn3NvT";

	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm
	// 对应APPID下的支付宝公钥。
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApRJEMhre1J+JhKZH5BlJhGZvJdO+rTwgm/7wuJY+eXKp2zOTubl9mGBT2l4trsADhuzdta5hzGa3TtmPh/iVjtPhPdwduNLCrD7lCLHj7d24YC3TKG5qRVVMk/d9RZ/X+yQ9K5T/7b47DW/LGabsnnch5iTXE3LFn0v9uxTyfahxEWiMj0hdmA+vZOQKZmT8gzNrewyJfjDtpnJKLkVWtNDzQeqvskis/lAEjOrkY2k/+OC2/Sp8vbwB+fX7zgJpzKoXUEQ0Tgf6CJtoPV3YWzCOr6IZpV2BgUS6jpJKtwU0frHg6oe8NjiqLzlzhjxkg84rK0KBA85UlZYh+Gc/KwIDAQAB";

	// 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://iai9tj.natappfree.cc/front/asynCallBack";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://iai9tj.natappfree.cc/front/synCallBack";

	// 签名方式
	public static String sign_type = "RSA2";

	// 字符编码格式
	public static String charset = "utf-8";

	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";//?charset=utf-8

	// 支付宝网关
	public static String log_path = "C:\\";

	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	/**
	 * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
	 * 
	 * @param sWord
	 *            要写入日志里的文本内容
	 */
	public static void logResult(String sWord) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
			writer.write(sWord);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
