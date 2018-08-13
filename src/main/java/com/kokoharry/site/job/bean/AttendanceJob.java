package com.kokoharry.site.job.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.quartz.*;
import org.quartz.Job;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Data
public class AttendanceJob implements Job {

    private String userName;

    private String password;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        log.info("Auto task start");

        try{
            //开启新WebDriver进程 打开浏览器
            WebDriver webDriver =new ChromeDriver();
            //页面最大化
            webDriver.manage().window().maximize();
            //全局隐式等待，等待
            webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            //设定网址
            webDriver.get("http://kq.neusoft.com");

            List<WebElement> list = webDriver.findElements(By.className("textfield"));
            WebElement element = webDriver.findElement(By.className("a"));
            //输入用户名
            list.get(0).sendKeys(userName);
            //输入密码
            list.get(1).sendKeys(password);

            WebElement ele = webDriver.findElement(By.id("imgRandom"));
            File screenshot = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
            BufferedImage fullImg =null;
            try {
                fullImg = ImageIO.read(screenshot);
            }catch (IOException e1) {
                e1.printStackTrace();
            }
            Point point = ele.getLocation();
            BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),60, 25);
            try {
                ImageIO.write(eleScreenshot, "png", screenshot);
            }catch (IOException e1) {
                e1.printStackTrace();
            }

            Tesseract tessreact = new Tesseract();
            tessreact.setDatapath("F:\\data");
            try {
                String result = tessreact.doOCR(eleScreenshot);
                element.sendKeys(result);
                System.out.println(result);
            } catch (TesseractException e) {
                System.err.println(e.getMessage());
            }
            //打卡
            webDriver.findElement(By.className("mr36")).click();
            //關閉
            webDriver.close();

            log.info("Auto task finished");

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}