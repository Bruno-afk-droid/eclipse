package com.firstproject.main;

import java.util.LinkedList;

public class Armor {
	
	double DamageMultipli�r=1;
	double HitstunMultipli�r=1;
	double KnokBackMultipli�r=1;
	
	boolean StatusImmume = false;
	
	public Armor(double DamageMultipli�r, double HitstunMultipli�r,double KnokBackMultipli�r,boolean StatusImmume) {
		this.DamageMultipli�r=DamageMultipli�r;
		this.HitstunMultipli�r=HitstunMultipli�r;
		this.KnokBackMultipli�r=KnokBackMultipli�r;
		this.StatusImmume=StatusImmume;
	}
	
	public Armor(double DamageMultipli�r, double HitstunMultipli�r,boolean StatusImmume) {
		this.DamageMultipli�r=DamageMultipli�r;
		this.HitstunMultipli�r=HitstunMultipli�r;
		this.StatusImmume=StatusImmume;
	}
	
	public Armor(double DamageMultipli�r, double HitstunMultipli�r, double KnokBackMultipli�r) {
		this.DamageMultipli�r=DamageMultipli�r;
		this.HitstunMultipli�r=HitstunMultipli�r;
		this.KnokBackMultipli�r=KnokBackMultipli�r;
	}
	
	public Armor(double DamageMultipli�r, double HitstunMultipli�r) {
		this.DamageMultipli�r=DamageMultipli�r;
		this.HitstunMultipli�r=HitstunMultipli�r;
	}
	
	public Armor() {
		
	}
	
	public Armor clone() {
		return new Armor(this.DamageMultipli�r,this.HitstunMultipli�r,this.KnokBackMultipli�r,this.StatusImmume);
	}
	
	public Damage Earn_Damage(Damage D) {
		Object[] s = null;
		if(!StatusImmume)
			s = D.StatusEffects;
		return new Damage((int)Math.floor(D.Damage*DamageMultipli�r),(int)Math.floor(D.Hitstun*HitstunMultipli�r),(int)(D.KnokBack*KnokBackMultipli�r),s);
	}
}
