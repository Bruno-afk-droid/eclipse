package com.firstproject.main;


public abstract class Fragment implements Cloneable {
	
	public Fragment H_Owner;
	public Fragment Owner;
	public Fragment[] Joint;
			
	public Fragment() {
		
	}
	
	public Fragment(Fragment[] Joint) {
		this.Joint = new Fragment[Joint.length];
		for(int i=0;i<Joint.length;i++)
		conect(i,Joint[i]);
		
	}
	
	public Fragment(Fragment[] Joint,Fragment Owner) {
		this.Joint = Joint;
		for(int i=0;i<Joint.length;i++)
		conect(i,Joint[i]);
		
		this.Owner=Owner;	
		
	}
	

	
	public Object getOwner(){
		return Owner;	
	}
	
	public Object getJoint(int i){
		return Joint[i];	
	}
	
	public int getIndex(Fragment Frag) {
		for(int i=0;i<Joint.length;i++)
		if(Joint[i]==Frag)
		return i; 
		return -1;
	}
	
	public void conect(int i,Fragment Frag) {
		Joint[i]=Frag;

		
		Joint[i].Owner=this;
		/*
		if(Joint[i].Owner!=null) 
		Joint[i].Owner.Joint[Joint[i].Owner.getIndex(Joint[i])]=null;
		 */
	}
	
	public void conect(Fragment Frag) {
		conect(0,Frag);
	}
	
	public void disconect(Fragment Frag) {
		for(int i=0;i<Joint.length;i++) 
		if(Joint[i]==Frag)disconect(i);	
	}
	
	public void disconect(int i) {
		Joint[i].Owner=null;
		Joint[i]=null;
	}
	

}


/*
 * 	public void add(Fragment Frag) {
		int i=0;
		while(Owner[i]!=null)i++;
		Owner[i]=Frag;
	}	
	
	public void remove(Fragment Frag) {
		int i=0;
		while(Owner[i]!=Frag)i++;
		Owner[i]=null;
	}
	
	public void Conect(int i,Fragment Frag) {
		if(Joint[i]!=null) {
		Joint[i]=Frag; 
		Joint[i].add(this);
		}
	}
	
	public void Disconect(Fragment Frag) {
		for(int i=0;i<Joint.length;i++) 
		if(Joint[i]==Frag) Disconect(i);

	}
	
	public void Disconect(int i) {
		Joint[i].remove(this);
		Joint[i]=null;
	}
*/
