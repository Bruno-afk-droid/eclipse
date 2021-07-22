package com.firstproject.main;

public class Damage {
	int Damage=0;
	int Hitstun=0;
	int KnokBack=0;
	
	boolean finisher = false;
	Object[] StatusEffects;
	
	public Damage(int Damage,int Hitstun,int KnokBack,boolean finisher,Object[] StatusEffects) {
		this.Damage=Damage;
		this.Hitstun=Hitstun;
		this.KnokBack=KnokBack;
		this.finisher=finisher;
		this.StatusEffects=StatusEffects;
	}
	
	public Damage(int Damage,int Hitstun,int KnokBack,Object[] StatusEffects) {
		this.Damage=Damage;
		this.Hitstun=Hitstun;
		this.KnokBack=KnokBack;
		this.StatusEffects=StatusEffects;
	}
	
	public Damage(int Damage,int Hitstun,int KnokBack,boolean finisher) {
		this.Damage=Damage;
		this.Hitstun=Hitstun;
		this.KnokBack=KnokBack;
		this.finisher=finisher;
	}
	
	public Damage(int Damage,int Hitstun,int KnokBack) {
		this.Damage=Damage;
		this.Hitstun=Hitstun;
		this.KnokBack=KnokBack;
	}
	
	public Damage() {
		
	}
	
	
	public Damage clone() {
		return new Damage(this.Damage,this.Hitstun,this.KnokBack,this.finisher,this.StatusEffects);
	}
	
}
