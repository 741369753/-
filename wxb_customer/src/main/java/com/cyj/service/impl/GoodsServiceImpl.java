package com.cyj.service.impl;

import com.cyj.dao.GoodsCopyDao;
import com.cyj.dao.GoodsDao;
import com.cyj.dao.GoodsSkuDao;
import com.cyj.dao.GoodsTypeDao;
import com.cyj.entity.Customer;
import com.cyj.entity.Goods;
import com.cyj.entity.GoodsCopy;
import com.cyj.entity.GoodsType;
import com.cyj.service.GoodsService;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * author:aizhishang
 * time:2020/8/30
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsDao goodsDao;
    @Resource
    private GoodsTypeDao goodsTypeDao;
    @Resource
    private GoodsCopyDao goodsCopyDao;
    @Resource
    private GoodsSkuDao goodsSkuDao;

    @Override
    public List<Goods> queryByCustomerIdLikeName(String customerId,String name) {
        return goodsDao.queryByCustomerLikeName(customerId,name);
    }

    @Override
    public int updateGoods(Goods goods) {
        return goodsDao.updateByPrimaryKeySelective(goods);
    }

    @Override
    public Map<String, Object> addPageInit(Customer customer) {
        List<GoodsType> goodsTypes = goodsTypeDao.queryAll();
        List<GoodsCopy> goodsCopies = goodsCopyDao.queryByCustomerId(customer.getCustomerId(),0);
        Map<String ,Object> map = new HashMap<>();
        map.put("type",goodsTypes);
        map.put("wxcopies", goodsCopies);
        return map;
    }

    @Override
    public String upload(MultipartFile imgFile) {
        System.out.println(imgFile.getOriginalFilename());

        String fileName = imgFile.getOriginalFilename();
        fileName = UUID.randomUUID().toString().replace("-", "")+fileName.substring(fileName.lastIndexOf("."));
        try {
            //使用FTP客户端连接和登录
            FTPClient ftpClient = new FTPClient();
            ftpClient.connect("192.168.220.139");
            ftpClient.login("root", "1");
            int replyCode = ftpClient.getReplyCode();
            //如果响应码在200到299之间，表示与FTP站点的连接是成功的
            if (FTPReply.isPositiveCompletion(replyCode)){
                //设置编码UTF-8
                ftpClient.setControlEncoding("UTF-8");
                //设置被动模式（腾讯云必须添加，其他云待测试）
                ftpClient.enterLocalPassiveMode();
                //a.设置接收文件类型为二进制文件
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                //b.在服务器上创建文件夹
                ftpClient.makeDirectory("/usr/local/nginx/projects/wxb/imgs");
                //c.切换进入到images文件夹
                ftpClient.changeWorkingDirectory("/usr/local/nginx/projects/wxb/imgs");

                //d.将文件上传到ftp服务器
                InputStream inputStream = imgFile.getInputStream();
                boolean b = ftpClient.storeFile(fileName, inputStream);
                System.out.println(b);

                inputStream.close();
                //2.退出登录
                ftpClient.logout();

                return fileName;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "fail";
    }

    @Override
    public int addGoods(Goods goods) {
        return goodsDao.insertSelective(goods);
    }

    @Override
    public int deleteGoods(String goodsId) {
        if (goodsId==null||goodsId.isEmpty()){
            return -1;
        }
        if (goodsDao.deleteByPrimaryKey(goodsId)>0){
            goodsSkuDao.deleteByGoodsId(goodsId);
            return 1;
        }
        return 0;
    }

}
