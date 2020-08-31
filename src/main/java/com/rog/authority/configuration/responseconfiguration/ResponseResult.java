package com.rog.authority.configuration.responseconfiguration;

import com.rog.authority.constant.sys.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: TODO
 * @Author Rogers
 * @Date 2020/5/24 14:56
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> {

        private int status;
        private String desc;
        private T data ;

        public static ResponseResult success() {
            ResponseResult result = new ResponseResult();
            result.setResultCode(ResultCode.SUCCESS);
            return result;
        }

        /**
         * 成功，创建ResponseResultUtil：有data数据
         */
        public static ResponseResult success(Object data) {
            ResponseResult result = new ResponseResult();
            result.setResultCode(ResultCode.SUCCESS);
            result.setData(data);
            return result;
        }

        /**
         * 失败，指定status、desc
         */
        public static ResponseResult fail(int status, String desc) {
            ResponseResult result = new ResponseResult();
            result.setStatus(status);
            result.setDesc(desc);

            return result;
        }

        /**
         * 失败，指定ResultCode枚举
         */
        public static ResponseResult fail(ResultCode resultCode) {
            ResponseResult result = new ResponseResult();
            result.setResultCode(resultCode);
            return result;
        }

        /**
         * 把ResultCode枚举转换为ResponseResultUtil
         */
        private void setResultCode(ResultCode code) {
            this.status = code.code();
            this.desc = code.message();
        }

    }
