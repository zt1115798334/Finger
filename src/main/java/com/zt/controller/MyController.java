package com.zt.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import zk.jni.JavaToBiokey;

import java.io.*;

/**
 * @author zhangtong
 * Created by on 2017/11/27
 */
@Controller
public class MyController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/regFinger")
    public String regFinger() {
        return "regFinger";
    }

    private static String saveRegisterTemplate;

    @RequestMapping(value = "regFinger", method = RequestMethod.POST)
    @ResponseBody
    public String regFinger(@RequestParam String registerTemplate,
                            @RequestParam String userId) throws IOException {
        System.out.println("registerTemplate = " + registerTemplate);
        saveRegisterTemplate = registerTemplate;
        String path = System.getProperty("user.dir") + File.separator + "fingerprint" + File.separator + "fingerprint.json";
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(userId, registerTemplate);
        FileWriter fw = new FileWriter(new File(path), true);
        BufferedWriter out = new BufferedWriter(fw);
        out.write(jsonObject.toString());
        out.newLine();
        out.flush();
        return registerTemplate;
    }

    @RequestMapping(value = "checkFinger", method = RequestMethod.POST)
    @ResponseBody
    public String checkFinger(@RequestParam String verifyTemplate,
                              @RequestParam String userId) throws FileNotFoundException {

        System.out.println("verifyTemplate = " + verifyTemplate);
        if (JavaToBiokey.NativeToProcess(saveRegisterTemplate, verifyTemplate)) {
            System.out.println("Success");
        } else {
            System.out.println("Failed");
        }
        return verifyTemplate;
    }

    public static void main(String[] args) {
        String reg = "mspY1o6mpgltAQiMDjZBB2mOVMIGCJBjAQWDkTRCBuIVZ0EJHJYqwQZoF2nBB40YNIIJZRpNQgt1oX2BBiChT8IWEiJYgg0bJnKBBiapMsELTy1hAgwvLnHBCC0vSEExODIoQQdMNDDBB0e2NsIIPTdEgSW0t0bBJL24cUEIMzhmwQ1FOUdBHmU5P8EvuDo8ASmyPGtBE0s9OwEd2z0mwQXDvoQCDDHBNgET2cFxQnc5wXxCHZpCL4IOyEN5gh1+SHhBTGnLKgEL1EtEgQPZUlRCBdRAVMsRr1BQHAMSUVNTU1ZhDR0mKSopJSMiIQMSUFBOTEtFMysvMC4tKSckJAMRVVZXWF1nAhUfIiUlIyMiAxNMTUtGQz87ODg2NDEsKSUlIgMRWVtcXmFqdg0XHCAiISEhAxNLSkdDQkNEQj87NzItKSYnJwMRX19hY2dtdgkQFhodHyAgAxNJSUhHS1BRTUhCOjMrKCQlIgQRZGZpbHEBBw4UGBscHR0DE0tLS1FYXVtXUktAMiQjIiQiBBBoaWxwdAIIDRIWGRscAxJQUFNYXV9dXFhWVDcaHRwgBBBoa21xdQMHDBAVGBobBBFUWFxfYF5eXFxhbQULBgQPaWxvcnYDBwsPExcaBBFZXV9gYF9eXV9iZm50cwUPbm9zdwQHCw8TFxsEEl1hYmJgXl5dXV9gYWdnaQYOcXR3BQgKDhEXBRFlZWNhX19eX2FgYmRjBw10AQYICw8RBhBnY2FfX15cXlxeXBjwMxm0qlAUIdEpxoHM/3yFVRjqjGWGC5QiDAUyDTjyIsNcu+jaTw83LB1wgCOdS+C3rKIgXACWPSVNC+Qed1knAK47O3XxTwUiLOWs40N8kl7txd5EtQ86Ifa/4CJYEZ2YQnvvnnmpKunFCTlIlwJhe3FAGb6Jit4F4UghJ/WtoJz1Y6GJNT3ZooaBs3bzRIj1YhTvaee46bYvxzIhpQpzxZUaNGDHHaKe7C7a8DTSLadWhQ56ujdO2K63C0KV71D/rYG/PcDOwlM2H2z7hwfgJvtCirZ6fHaLK8IfUoauQUsIi+Mk1m7jJZ08QfhYfWqCYg3/vR1bO5l3SmnxntfjShb6IMbriVRC4IH9wuGKICXvQzpzCZV8cP9GOrBV0XWFdZQC7xJen9ZsXDijOHcYGBgYGBgYGBgYGBgYGBgYGBgYGBgYGBgY";// "REGSTR";
        String ver = "mspYlpG2qAdaAguNjHKBFY8NS4EJEBB3ghGdljOBBm8WaoEIkRd6QQcgmTLBBewZUMIHEZtewgSKoSyBDWkkREINeiRiQgWVqUuBDoOqUIEKHa9yAQYlsidBDFKzZUEGKjkfQglQuTtBJD65U8EKOLoTARXJu2IBCDG8I8EKTD8qQglCv4iBE6FBNgErusE4QSu/QjLBO7bDLkE0tEQ6QRZlxFaBDklEYoEHNkQZwQXExSzBJODIWoETT8kmgRbeySDCEcxJdsEGn0xqQRWkzWCCbT5PUYENcFBnQhuD0E6BCGLRdgETikO07Q6ycPMXAxFgYWNpcQYTGR0gISIhISECEVhcXF5lcQobISQlJSQiIiMDEWNkaG11Bg8WGh0fIB8fHQIRVFZXWGBxFSInKisoJSQjJgMRZWltcncHDxQYGx0dHx0fAhFQUlJRUj4pKy4tLSonJSUpBBBscHQCBw8TFxocHR0dAhFMTktIRD44NjUyMCwoJicrBBBvcXQCBw0SFRkcHR8gAhFLTEdEQkE/PTk2My8sKCgqBBBucXQCBw4RFRgbHB8hAhFNTUpKTE5KRD85NTAtKSgpBRBydAEFDREVFxwdISECEVBQU1ZdXFZPRj42MCwoJycFD3N0dwQMDxQWGx0jAhBSVVtgY2BdVk8/MiglIiMGDnJ2AwoQFhgbHQIQWF5hY2NhYFxbUSQgHRwfBw51AgkQFhcYGgMQYmNlZWNiX2FrBRQUFBUIDQIJExgZGQQPZ2dlY2JhZGl1Cw8NGPACGbSqUBQh0SrOMS9lRQeSZl3DX1bRn8Pppxb2h01LIkrB6I3eGvzcTzWMpXuDQVXbMyvL6hic1u0P8FZUTvY4GjPt9nFMk37V4eLKV1JWgNSi31RcYAlAytXsz4LS8qm+7RQjXgUpwBAtqly2ZU+DCKAlWrjKcmUrnWEqNZ6ofqk2JrrSomNNUS8DLZKyU1kk6FILgv9eK7feykWNmwwApDMnZ8KB4Mpi+7jy5mP5Bef6OGLgtYXAq7rSlIEt4gKlFSLRrwEYs/uEK4gwDN49Vu6TzvUC6e6HvHovljRs2U5JY4yGhdKeAdexOQB7J1VK1vRhnaUpNV+eX97AzsvYiVoLHfkUQAuqjo+/jOccGTpOcKB+HLcYtCooyHcYGBgYGBgYGBgYGBgYGBgYGBgYGBgYGBgYGBgYGBgYGBgYGBgYGBgYGBg=";// "VERSTR";
        if (JavaToBiokey.NativeToProcess(reg, ver)) {
            System.out.println("Success");
        } else {
            System.out.println("Failed");
        }

        reg = "TfhTUzIxAAAEu7gECAUHCc7QAAAcumkBAAAAhGYsmbvBADQLpwByACKxwQDUAKUOWQCduxEL2QDgAG8PnLt6AAkPdgDZAay0lwBwAHcP/wDGu1EMfAAvAV0PXrsoAZkP2QCjAJK36gBzAB8L/AB8u18J7wBFAbkHiLu+AEINlgB0AFexugCoAI4NYwCJu3wOVwDgAPkP7bvrAK8P2wDJAZW1uQAmAZMPkwCBu+kPUwAaAWoKk7s+AY8PUQDrAYq3pwBOABANpQBUu2wPtAAXAMIMpbviACkPewB7AN61oACeAH8LsACRu1oPwACHAFcP57v7AJ0OSQAxADOy+QDAAKsI/wCqu1oO7gASAUkOALr4AKALOwDcAZ+66AAxAYIOkgBCuogPMBP94BT0uEzo1UGa7f+PfYY5kZJNlL2+KP1tvAgPMJedjnf4CapLEF75QHPz/NrR8PVSF2cZWPH6s3MBVA+V7OgTAcFHBn+K/YoWjMcoN3hjBgPyfP3DSrrvQRDBaNzwWZuIED0tWWpEE75HYGxRAal2WAbiVCZlIRRpBSMQxU78BcLlVgYTB/+qK4yujfONxBgRmlQWivja/lLo2Ey44SkGxpp6CIPFXoZ3Eiv7AvqvyMqTRoUW9cfrrSOXiKqpoHIIBV2Vu3UqTmKCoBcyv1cWsfA1DdQTIUWDipqeNgPj/wu0ZA3/def+RP2hSD8MdvBiBPsU7VUHDPoFNQkD+LZJrP698+Lzav3Qg7ZmIDwBAkIhVL4BAQUGwlTWADq39v/+wjbCOMX4emPA+PgEAHIUDZUCAOoX/f/IAFOj+8H+/zfAO0wLu0om+sFLQzhXUKoBPDft/WQ6K/v/RRIAMUnmO/5fRcH+PlRECsVmSkxAwf4rBQCbT3B5aAUAqE8TBUoXuzFS6///TwT++0XBPsD+cAXFX1DWwF4TADFdI//6RFL9VP7/wIf/Arvcah5R/wbFkmrGl8AKAJpxw/8sREUKAJJzd0HAwHuJFAA+dOL9M/pE/8E+P0sExe5xpcH/AwA7e5vBALuYfQMoEwB6hJN4l4PDdf/Ch8UAu6WLhp8LAAGMGET/WP58GQBhkHl5wsPDwcDCBInFRMXBw8DDZK8JBBeRE/8oRw/FdJDZiMLAYcDDBZkOu5+WA/z8RDpsCLt3mVqLwcOqwcevAZaZcMTCBneHN8LBw/6DBMWepM/FwhsAJ6QSwPvwOv7///7/Bf7ERcD+wf7//wT+HrsrrtpoOP46LvtFVT44VgMA/7FTew0ApLUwwAfAxsdrCgCeuTEFwm3EFgD7vq3BtGnGOsT/w8DDwATA+XgLAIjCSZMEwsZ5ww4AjsJAQcGXKUoDADfDUwUIBCbFOsLDacPBADh+TVkEAPvG9YECu7fKKcHBe9AA3WSo/3nDxsIBwpvVw8HDwwcAneNEM5wIAKLmK0fCWb4AFvRAwFnGAE1MQsUbAQP4ZSlzyojEwsLAxAR4xUTBwRoREAxKwPpGwMD+/sLCBsnDfsDCwMHEwQfCxXvDAxA1IhAFBBQFKQZsDxAy6Qn7ePrCwGvC/wdgAatgLRPCUQ/VMjC3X8P/wljCiwMUjD8QwwcQnYUJctYJED1LCcM4ZMU=";
        ver = "TfNTUzIxAAAEsLQECAUHCc7QAAAcsWkBAAAAhF0qgrDGAK4OZQAWAC6/YACgAFwKaADbsLIPaACGANQPnrABAZUOxgAqAKy/dwAaAZUPfgBhsCQPqwAlAUEPObAhAZ8OrAD5AXm/fgA2ABUPDgA/sXsLegCsAOgMpbDTAK0PVABqAEK6RACtAN4NBAC1sLEPjAB2AFMPqbAGAZAOIgAJAES8YwBbAHkP9QAKsa8PVAAxAVcP2rBpAKQOtQCAAf2/PAAxAHEPqwCtsB4LhwCUAFMOarCTAHQOpwA1AJu+cgB9AHwPFADHsCsPQwCAAJkPaLBjABQP0QDCAZW/rQBSAJwP7ABvsOoPEQAHAXYMe7BPAYMOzAD1AKS54AOCgEaFj30CViMPPTwF6UQZdbkcFeYMnJ+QlL0H+AXJ+cLmzBt2RFsIVgbSA+IVBTpjgc+RPQoH9DY9iPL9D3vt4YfFSgMEy5y/AEZ5cTKH/KL63BPsm+Fj9P1lAw4QBGgdXTArqZiShGfC8ToyBCP9YR/3AIY2gP2l8jYM4mQrT46PIGc2/odr/aJf/PMdifcT/otVY4FvCpMH7X9xMj91M/pfi9JfaEf4xCGubSaEa7awuWdtGdX6dO05vRgSaWxNCjzr/c6jh794wnDrTUKzBZ+hm7IFWOt2sYb0CXrH/IYItiCKd2d/2gQiFZ/H7giX8bvxhgQrzhb/mJ39W3oDJI8AApQiOgbFlASgLv8JAKgAyihRTgYAgwIQPo8EBGQMAGUUACbK8DZw//5AW/9F9AMFsxJ6/xIAI9/6b4E3PlXAFQDeJvPowf3A/v//+/9gcC4EADsvdLkKBPMx9zPA/zHTABeF7P/9wv9AOMD7T/7/wMH+wQX+xbUBPDVwwHDBANSGI1QEAIE51j4csBlR6f/AwO39+XDA/////sAF/8Rx/nAFAGJYRZMNsLBYIMH+wAX/X6EBalsJ/v84RPtPVovGDABimHqEccDBwsB/A8VuZ7n8CAC8aSSBwV6qAQdv3sD++/76gz84wD6G/9UAjsaXwcDFw3xVasS+AZF6IsD9ksH6IsLCDQBJfTX++E3+KUvBHACqf4dzxIJug2nERcHEwEMPAEGAZEzBxHGBwsGhBgCBhFhxwlADAGqFxvsTsAGG2kb/ODj+xEzB/sH+/8A6/sRPGABih3Gfu8HHcMCRlH7C/oEUBLWN3lMw/yf+wDxPBABtkoPGYwYEPJ0p/l0MAJqgWHDFw3fBxMAGCATfqSb+wcHBBMAOsEqt3P39/D7+KaEBVrFGhJZXYsXrCgBFtUl/AcCJcwgAabU3wQbBxDcEANDEN5nBANF0J2QGAB/Ng4nGuwFvtDB1fDqIAbAnzz3AjQbFa9CZwMDEwggAo9UwJsRZGQAI1xVkMk4w+zUpN/+4BgQB5CKUwRIQxAItcv/DicDAgQXAx/QcEM8DkC4EwflOw8LGyMbEBMLEc8PBw8HBwAb+xHAGEBULJMABwftyBBC0CwzBmAQUzhwJbAgQGfcPY+IGEFs0DH6hCBSsPxB4YgQQGj9+5gURFEBXxj8GFK1ID8HAwv/DEIjo/MDB/sIH1dJOzVNKAxBHYEX/";

        if (JavaToBiokey.NativeToProcess(reg, ver)) {
            System.out.println("Success");
        } else {
            System.out.println("Failed");
        }
    }


}
