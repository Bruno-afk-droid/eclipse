package com.firstproject.main;

import java.util.LinkedList;

public class Armor {
	
	double DamageMultipliër=1;
	double HitstunMultipliër=1;
	double KnokBackMultipliër=1;
	
	boolean StatusImmume = false;
	
	public Armor(double DamageMultipliër, double HitstunMultipliër,double KnokBackMultipliër,boolean StatusImmume) {
		this.DamageMultipliër=DamageMultipliër;
		this.HitstunMultipliër=HitstunMultipliër;
		this.KnokBackMultipliër=KnokBackMultipliër;
		this.StatusImmume=StatusImmume;
	}
	
	public Armor(double DamageMultipliër, double HitstunMultipliër,boolean StatusImmume) {
		this.DamageMultipliër=DamageMultipliër;
		this.HitstunMultipliër=HitstunMultipliër;
		this.StatusImmume=StatusImmume;
	}
	
	public Armor(double DamageMultipliër, double HitstunMultipliër, double KnokBackMultipliër) {
		this.DamageMultipliër=DamageMultipliër;
		this.HitstunMultipliër=HitstunMultipliër;
		this.KnokBackMultipliër=KnokBackMultipliër;
	}
	
	public Armor(double DamageMultipliër, double HitstunMultipliër) {
		this.DamageMultipliër=DamageMultipliër;
		this.HitstunMultipliër=HitstunMultipliër;
	}
	
	public Armor() {
		
	}
	
	public Armor clone() {
		return new Armor(this.DamageMultipliër,this.HitstunMultipliër,this.KnokBackMultipliër,this.StatusImmume);
	}
	
	public Damage Earn_Damage(Damage D) {
		Object[] s = null;
		if(!StatusImmume)
			s = D.StatusEffects;
		return new Damage((int)Math.floor(D.Damage*DamageMultipliër),(int)Math.floor(D.Hitstun*HitstunMultipliër),(int)(D.KnokBack*KnokBackMultipliër),s);
	}
}
