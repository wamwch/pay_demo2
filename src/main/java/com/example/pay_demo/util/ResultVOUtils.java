package com.example.pay_demo.util;

/**
 * @author:ljf
 * @date: 2019/12/15
 * @description:
 **/
public class ResultVOUtils {

    public static ResultVO ok (Object obj){
        ResultVO resultVO = new ResultVO();
        resultVO.setSuccess(true);
        resultVO.setData(obj);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO success(Integer code,String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setSuccess(true);
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return  resultVO;
    }

    public static ResultVO date(Integer code,String msg,Object object){
        ResultVO resultVO=new ResultVO();
        resultVO.setSuccess(true);
        resultVO.setMsg(msg);
        resultVO.setCode(code);
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO error(Integer code,String msg){
        ResultVO resultVO=new ResultVO();
        resultVO.setSuccess(false);
        resultVO.setMsg(msg);
        resultVO.setCode(code);
        return resultVO;
    }
}
