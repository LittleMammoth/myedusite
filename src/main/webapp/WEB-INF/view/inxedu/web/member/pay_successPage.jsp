<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	//全局变量 
	var outTradeNo = '${outTradeNo}';
	var tradeNo = '${tradeNo}';
	var totalAmount = '${totalAmount}';
</script>
</head>
<body>
<h3>您的订单号为 ${outTradeNo} ,支付宝F易号${tradeNo} ,支付成功了一笔${totalAmount}元。</h3>
</body>
</html>