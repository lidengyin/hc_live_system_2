package cn.hctech2006.livesystem1.service.impl;

import cn.hctech2006.livesystem1.bean.Cause;
import cn.hctech2006.livesystem1.common.ServerResponse;
import cn.hctech2006.livesystem1.mapper.CauseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CauseServiceImpl {
    @Autowired
    private CauseMapper causeMapper;
    public ServerResponse causeUpload(String license){
        Cause cause = new Cause();
        cause.setLicense(license);
        cause.setFlag(0);
        int result = causeMapper.insert(cause);
        if (result > 0) return ServerResponse.createBySuccess("肇事车辆上传成功");
        return ServerResponse.createByError("肇事车辆上传失败");
    }
}
