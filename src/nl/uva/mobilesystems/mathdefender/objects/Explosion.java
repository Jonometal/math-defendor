package nl.uva.mobilesystems.mathdefender.objects;

import nl.uva.mobilesystems.mathdefender.GameModel;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEventListener;
import nl.uva.mobilesystems.mathdefender.gui.TexMan;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;


/** Class representing single "enemy" in wave **/ 
public class Explosion extends AnimatedSprite{
	
	private GameModel model;

	private ObjectPositionEventListener listener; 
	
	
	private Text myText;
	private Font myFont;
	
	public Explosion(final float pX, final float pY, 
			final VertexBufferObjectManager pVertexBufferObjectManager, Font myFont, GameModel model)
	{
		
		super(pX, pY, TexMan.getIt().mTowerKillerTextureRegion, pVertexBufferObjectManager);
		this.model = model;
		this.myFont = myFont;
		myText = new Text(0,0, this.myFont, "Equation", 50, pVertexBufferObjectManager);
		myText.setText("boobs");		
		this.attachChild(myText);
		
//	    PointParticleEmitter equationExplosion = new PointParticleEmitter(pX, pY);
//	    ParticleSystem ExplosionParticleSystem = new ParticleSystem(equationExplosion, 45, 60, 60);
//
//
//	    ExplosionParticleSystem.addParticleInitializer(new AlphaInitializer(1f));
//	    ExplosionParticleSystem.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE);
//	    ExplosionParticleSystem.addParticleInitializer(new VelocityInitializer(-175, 175, -175, 175));
//	    ExplosionParticleSystem.addParticleInitializer(new RotationInitializer(0.0f, 360.0f));
//	    ExplosionParticleSystem.addParticleInitializer(new RotationInitializer(0f, -20f));
//
//	    ExplosionParticleSystem.addParticleModifier(new ScaleModifier(1.0f, 0.5f, 0, MAX_PARTICLE_LIFE/2));
//
//	    ExplosionParticleSystem.addParticleModifier(new AlphaModifier(1, 0.35f, 0, MAX_PARTICLE_LIFE));
//
//	    ExplosionParticleSystem.addParticleModifier(new ExpireModifier(MAX_PARTICLE_LIFE, MAX_PARTICLE_LIFE));
//
//
//	    this.attachChild(ExplosionParticleSystem);
	}
	
	public synchronized void addObjectPositionEventListener(ObjectPositionEventListener listener){
		this.listener = listener;
	}
	
	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		if(pSecondsElapsed > 0.2){
			myText.setText("and");
			super.onManagedUpdate(pSecondsElapsed);
		}
		else if(pSecondsElapsed > 0.5){
			myText.setText("explosions!");
			super.onManagedUpdate(pSecondsElapsed);
		}
		else if(pSecondsElapsed > 1){
			model.removeObjectFromScene(this);
		}
		else{
			super.onManagedUpdate(pSecondsElapsed);
		}


	}
	
}