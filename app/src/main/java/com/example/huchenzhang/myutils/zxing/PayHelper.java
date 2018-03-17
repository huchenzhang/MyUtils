package com.example.huchenzhang.myutils.zxing;


/**
 * 支付
 * Created by huchenzhang on 2018/3/17.
 */

class PayHelper  {
    //沙箱APPID
    static final  String app_id = "2016101800718925";
    //沙箱私钥
    static final  String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDfDBUJhaw46ck8MHccIBXPt2USOvCRZ0dQNZZwC0EmZvcnL2+NUyoJMbgcV3U5o6wApajkpibf9UkrtYgI3/GZhOgzfzH0IJYAXmYCEniM+Cl5ipIWhY0W/s+uNRGNdXvNlketNNcVqAvkKZdcHz23N60BXsvjdR+BJ/np2CUmr9OCv0aqqwOO190rOuZvVaFCJD5LDmvDFdXPzrJHcOAyYjb2hhJIWt5n4o/b3LMYeuxfwWuQ4VlGx+s0wZJ1udUyAX8X8byqYcnevG8A1sQd/dRVNr8f1BxS8kjDIDljCzKPoYyoaoeJMhJIxrV8gL57oEIX7PbrlvFLplaC7g/XAgMBAAECggEAI4m/SFceC6tkPszSDY8nKoNj/TWa4u/7stH2+D8y0DWTp5CtS202w2RCm3Fr4hUr8KZ0dInso75nPCmeVRupWPaOMLZRdT5D6CciRa4/uuZHZXAouqHoywMqf+1AsLNJWIZbQUOsCW1PB7mAvlLT+H9Md21tOv6MWH3QGewwbytEcri0YkzroQDso3P08xdZDpXtrmg34tLnrNhw+gl8LOtpkbd0SOrGAsoTAE1vTbSLtTR2PAW7RQZkyKW1ansouzwduamFMhTc+DTc40ODJ5b5xlwTDrXn681E7UTX7JytYTPmjrKFHceX9hQ3vxSkm0fEQ7sXoF0pOBL+7AkOIQKBgQD00qCxCSVE8x2Xb/shayGBLCumqIzHtH5Eb42Wz8AOCH2/Ns9HahzXCJfKWhEzWPltYdD34xlu4T+6r7kN9hNNV7PdHRWMMNyFlUrJ8zSzScu13KdQCx9r+JszD8VRRthNHtWDevj83p3qbwr6rNFw6L7nyjxjRE4RaA56gKdQrwKBgQDpOvOz6JaD941qi7jFxnbOZh2OdRrDUHkCG8Cx42eDA83aCCjLJznjJdLXsF2HON2YDcXOn2pHyROks6a+pZZ3nP3VOX5R06QuPrW5cqF3kxO7+v25DVPI4F3VWz+2mbU5uxlqWIRTZikfnvzZeVQcGgFh3ektI6lVDAo6eD/tWQKBgAEy+uiDE/SngDCass8yGCLq1XfmgXIE901cgoQHlng7IGqMWS2PXn7LIvsxv73RpL8daImeSiSZ49cFP1HLL8MEN/RQTSe3oYzh81DT6SErrlzArO0ecNP1jsUMQbopL6wJB6CyPH/4kytvycz6hbgY276E5hDSZ9N7//4nnSovAoGAP9KgCeVl+urtSfC+OXDb5hNPl0I4dqY41vjdPFCsbM/Hrxh4gU9UqfIZxmg+z9gh25A5Rxecr9Q4ieOXh6gsisLtO0ImbSHrVbRXak4SHXSj+9btQ4yAFwI+6zmzL7bUPt4SUGW0oFXefwa0zpZDDAWZxzxAqNePaPSIgAOUCtECgYEA8zySp0UdBQQOEJuTUJlHgf+FK0jUMREVBi9CQgbK8iF/hDmA34B4z0JCkWEsGojiS3S72ZJBXxrZ8RowJGqLuNZHUAD+eEzjoBhZOIfn2R0Bo2UAZAe6pwERzqOEEVLyBExEV3+qkDwIewx4lghFCQpwhO6SNWj4ppSgXh7XAss=";
    //支付宝公钥
    static final  String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnVuBUzhfo7kWUN/TS/kHmb4afsAlUVaTR3U5e74V0os1bzlfvh+cpwnwzaEIb36CR1OzeIIzXgARIW/zGAL1/PLG8dFEXQOIGkA0a8YXGVNX9KeWnFCnCQ5gQxrfJ3ryasXzWBxv5bex1VrVsQ2TZ1VdUD+S7dR4SWFHvyck8/xGt3Hz5peUqo/OIlfQpoTBXdqfnJNrTVAMqcQhrIWwgpSVgmUq+UhlZsDaiUvxWWFm9v9nY172jFfzup/DIET//OpIghL6AB2F+ywkCcHdZHiGklnpNoQKQ6XMVKaO/k2nlHU64Y4WGSgJnSG6B798ohZoi3pndrVPAH5+5BKEFQIDAQAB";
    //沙箱网关地址
    static final  String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    // 签名方式
    static String sign_type = "RSA2";
    // 字符编码格式
    static String charset = "utf-8";
    //
    static String format = "json";

}
