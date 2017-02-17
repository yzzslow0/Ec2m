package com.easycode.retrofit;

/**
 * Created by yzz on 2016/9/20.
 */
public class MResponse{
        private String message;

        private int code;

        public boolean isSuccess(){
                if (code==0) {
                        return true;
                }else {
                        return false;
                }
        }


        public String getMessage() {
                return message;
        }

        public void setMessage(String message) {
                this.message = message;
        }

        public void setCode(int code) {
                this.code = code;
        }

        public int getCode() {
                return code;
        }
        }
