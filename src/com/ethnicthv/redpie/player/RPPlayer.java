package com.ethnicthv.redpie.player;

import com.ethnicthv.redpie.util.Math;
import org.bukkit.Bukkit;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RPPlayer implements Serializable {
    private static final long serialVersionUID = 1237187391031321L;

    private final UUID uuid;

    private RPPlayerMeta meta;

    private double HP,MP,ST;

    private double EXP;

    public int StatPoint = 0;

    public RPPlayer(UUID uuid){
        this.uuid = uuid;
        this.meta = new RPPlayerMeta();
    }

    public void update(){
        double curHP = HP/meta.getMAXHEALTH();
        double curMP = MP/meta.getMAXMANA();
        double curST = MP/meta.getMAXSTAMINA();
        this.meta.update();
        this.HP = curHP * meta.getMAXHEALTH();
        this.MP = curMP * meta.getMAXMANA();
        this.ST = curST * meta.getMAXSTAMINA();
    }

    public void setMeta(RPPlayerMeta meta) {
        this.meta = meta;
        update();
    }

    public void addEXP(int exp){
        int m = getEXPNeeded();
        if((EXP + exp) <= m) {
            EXP += exp;
            return;
        }
        EXP = EXP + exp - m;
        this.meta.LEVEL ++;
        update();
        return;
    }

    public void setEXP(int exp){
        int n = getEXPNeeded();
        this.EXP = Math.clamp(0, n, exp);
    }

    public void setLevel(int level){
        this.meta.LEVEL = level;
        update();
    }

    public double getEXP() {
        return EXP;
    }

    public double getHP() {
        return HP;
    }

    public double getMP() {
        return MP;
    }

    public double getST() {
        return ST;
    }

    public int getStatPoint() {
        return StatPoint;
    }

    public boolean drainMana(double manacost){
        if(this.MP < manacost){
            //do something
            return false;
        }

        this.MP -= manacost;
        return true;
    }

    public boolean dealDamage(int damage){
        if(this.HP < damage){
            Bukkit.getPlayer(uuid).setHealth(0);
            return false;
        }

        this.HP -= damage;
        Bukkit.getPlayer(uuid).setHealth((this.HP/this.meta.getMAXHEALTH())*20);
        return true;
    }


    public boolean drainStamina(int drain){
        if(this.ST < drain){
            //do something
            return false;
        }

        this.ST -= drain;
        return true;
    }

    public int getEXPNeeded(){
        return meta.LEVEL>10?meta.LEVEL*100:meta.LEVEL*125;
    }

    @Override
    public String toString() {
        return "RPPlayer{" +
                "uuid=" + uuid +
                ", meta=" + meta.toString() +
                ", HP=" + HP +
                ", MP=" + MP +
                ", ST=" + ST +
                ", EXP=" + EXP +
                ", StatPoint=" + StatPoint +
                '}';
    }

    public RPPlayerMeta getMeta() {
        return this.meta;
    }

    public void addLevel(int plus){
        this.meta.LEVEL += plus;
        this.StatPoint += plus;

    }

    public class RPPlayerMeta implements Serializable {

        private static final long serialVersionUID = 210312390138L;

        protected double ATK = 0,MATK = 0;
        protected double DEF = 0,MDEF = 0;
        protected double SPD = 0;
        protected double EVA = 0.1;
        protected double CRR = 0.05, CRD = 0.5;

        protected int LEVEL;
        protected double MANA = 0;
        protected double HEALTH = 0;
        protected double STAMINA = 0;

        protected Map<ProfessionID, Integer> PLEVEL;
        protected int AGI,STR,DEX,VIT,INT;
        protected int LUC;

        public RPPlayerMeta() {
            this.AGI = 0;
            this.STR = 0;
            this.DEX = 0;
            this.VIT = 0;
            this.INT = 0;
            this.LEVEL = 1;
            this.PLEVEL = new HashMap<>();
        }

        private void update(){
            this.HEALTH = 40 + LEVEL*5 + VIT*5;
            this.MANA = (int) (20 + LEVEL*2.5 + INT*5);
            this.STAMINA = 100 + Math.clamp(0,100,STR);
            this.ATK = 5 + LEVEL + STR*3 + DEX*2 + AGI*2;
            this.MATK = 5 + LEVEL + INT*3;
            this.DEF = 5 + LEVEL + VIT;
            this.MDEF = 5 + LEVEL + VIT + INT;
            this.SPD = AGI*0.2;
        }

        public int getVIT() {
            return VIT;
        }

        public void setVIT(int VIT) {
            this.VIT = VIT;
        }

        public void setSTR(int STR) {
            this.STR = STR;
        }

        public void setINT(int INT) {
            this.INT = INT;
        }

        public void setDEX(int DEX) {
            this.DEX = DEX;
        }

        public void setAGI(int AGI) {
            this.AGI = AGI;
        }

        public int getSTR() {
            return STR;
        }

        public int getINT() {
            return INT;
        }

        public int getDEX() {
            return DEX;
        }

        public int getAGI() {
            return AGI;
        }

        public double getMAXHEALTH() {
            return HEALTH;
        }

        public double getMAXMANA() {
            return MANA;
        }

        public double getMAXSTAMINA() {
            return STAMINA;
        }

        public double getATK() {
            return ATK;
        }

        public double getCRD() {
            return CRD;
        }

        public double getCRR() {
            return CRR;
        }

        public double getDEF() {
            return DEF;
        }

        public double getEVA() {
            return EVA;
        }

        public double getMATK() {
            return MATK;
        }

        public double getMDEF() {
            return MDEF;
        }

        public double getSPD() {
            return SPD;
        }

        public int getLEVEL() {
            return LEVEL;
        }

        @Override
        public String toString() {
            return "RPPlayerMeta{" +
                    "PLEVEL=" + PLEVEL.toString() +
                    ", AGI=" + AGI +
                    ", STR=" + STR +
                    ", DEX=" + DEX +
                    ", VIT=" + VIT +
                    ", INT=" + INT +
                    ", LUC=" + LUC +
                    '}';
        }
    }

    public enum ProfessionID{
        ONE_HANDED_SWORD,TWO_HANDED_SWORD,BOW,POLEARM,KATANA,WAND;

    }
}
