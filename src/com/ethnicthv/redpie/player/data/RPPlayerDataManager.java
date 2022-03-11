package com.ethnicthv.redpie.player.data;

import com.ethnicthv.redpie.Redpie;
import com.ethnicthv.redpie.player.RPPlayer;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RPPlayerDataManager {

    private static RPPlayerData playerdata = new RPPlayerData();

    private static Logger log;

    public static final File path = new File(Redpie.plugin.getDataFolder().getAbsolutePath() + File.separator + "data");
    
    public static void setup(Plugin plugin){
        log = plugin.getLogger();
        if(!path.exists()){
            path.mkdirs();
        }
    }

    public static RPPlayerData getPlayerdata() {
        return playerdata;
    }

    public static void save(UUID uuid, boolean deleteOnliveData) {
        //tạo file chứa
        File file = new File(path,uuid.toString() + ".yml");
        FileOutputStream fileOut;

        //hàm save
        try {
            //
            file.createNewFile(); //test tạo file, tạo đc thì trả true, ko tạo đc trả false
            //

            //lấy data người chơi từ onlive database
            RPPlayer rp = getPlayerdata().getPlayer(uuid);
            //

            //check data có null ko, có thì tạo data mới gắn vào uuid này
            if(rp == null){
                rp = new RPPlayer(uuid);
            }
            //

            //hàm save file//
            fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(rp);
            out.close();
            fileOut.close();
            //-------------//

            //bool là biến hỏi có xoá data người save khỏi onlive database ko
            if(deleteOnliveData){
                String s = uuid.toString()+ " " + getPlayerdata().removePlayer(uuid)? "Deleted" : "Cannot Delete";
                log.log(Level.CONFIG, s);
            }
            //
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public static void load(UUID uuid){

        //check xem sv đã có player này chưa, có thì thôi ko load nữa
        if(getPlayerdata().hasPlayer(uuid)){
            log.log(Level.CONFIG,"This Player data is onlive");
            return;
        }

        //Tạo file chứa
        File file = new File(path,uuid.toString() + ".yml");
        FileInputStream fileIn;

        //biến rp sẵn dùng chung
        RPPlayer rp;

        //hàm load
        try {
            if(file.createNewFile()){
                //hàm create tạo file sẽ trả về true, check xem đã có file chưa
                //chưa thì tạo và trả về biến rp mới mà data
                System.out.println("The data file not exist");
                rp = new RPPlayer(uuid);
            }else {
                //hàm load file//
                fileIn = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                rp = (RPPlayer) in.readObject();
                in.close();
                fileIn.close();
                //-------------//

                //check load xong có null ko, có thì tạo biến data mới
                if(rp == null){
                    log.log(Level.WARNING,"The data file return null");
                    rp = new RPPlayer(uuid);
                }
                //
            }
            //save vào onlive database của sv
            getPlayerdata().addPlayer(uuid,rp);
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
    }
}
