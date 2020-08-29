package cn.hctech2006.livesystem1.controller;

import cn.hctech2006.livesystem1.common.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "肇事车辆匹配控制器")
@RestController
@RequestMapping("/cause")
public class CauseController {
    @ApiOperation(value = "肇事车辆基本信息上传")
    @ApiImplicitParams({
            @ApiImplicitParam(type = "query", name = "license", value = "车牌号", required = true, defaultValue = "鲁ACW888")

    })
    @PostMapping("/cause_upload.do")
    public ServerResponse causeUpload(String license){
        return ServerResponse.createBySuccess();
    }

}
