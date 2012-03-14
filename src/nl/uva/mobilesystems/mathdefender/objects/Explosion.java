package nl.uva.mobilesystems.mathdefender.objects;

import nl.uva.mobilesystems.mathdefender.GameModel;
import nl.uva.mobilesystems.mathdefender.gui.TexMan;

import org.andengine.entity.particle.SpriteParticleSystem;
import org.andengine.entity.particle.emitter.CircleOutlineParticleEmitter;
import org.andengine.entity.particle.initializer.AlphaParticleInitializer;
import org.andengine.entity.particle.initializer.BlendFunctionParticleInitializer;
import org.andengine.entity.particle.initializer.ColorParticleInitializer;
import org.andengine.entity.particle.initializer.RotationParticleInitializer;
import org.andengine.entity.particle.initializer.VelocityParticleInitializer;
import org.andengine.entity.particle.modifier.AlphaParticleModifier;
import org.andengine.entity.particle.modifier.ColorParticleModifier;
import org.andengine.entity.particle.modifier.ExpireParticleModifier;
import org.andengine.entity.particle.modifier.ScaleParticleModifier;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.opengl.GLES20;


/** Class representing single "enemy" in wave **/ 
public class Explosion extends AnimatedSprite{
	
	private GameModel model;
	
	private float timer = 0;
	
	public Explosion(final float pX, final float pY, 
			final VertexBufferObjectManager pVertexBufferObjectManager, GameModel model)
	{
		
		super(pX, pY, TexMan.getIt().mParticleTextureRegion, pVertexBufferObjectManager);
		this.model = model;
		
		final CircleOutlineParticleEmitter particleEmitter = new CircleOutlineParticleEmitter(0, 0, 10);
		final SpriteParticleSystem particleSystem = new SpriteParticleSystem(particleEmitter, 10, 20, 25, TexMan.getIt().mParticleTextureRegion, this.getVertexBufferObjectManager());

		particleSystem.addParticleInitializer(new ColorParticleInitializer<Sprite>(1, 0, 0));
		particleSystem.addParticleInitializer(new AlphaParticleInitializer<Sprite>(0));
		particleSystem.addParticleInitializer(new BlendFunctionParticleInitializer<Sprite>(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE));
		particleSystem.addParticleInitializer(new VelocityParticleInitializer<Sprite>(-2, 2, -10, -10));
		particleSystem.addParticleInitializer(new RotationParticleInitializer<Sprite>(0.0f, 360.0f));

		particleSystem.addParticleModifier(new ScaleParticleModifier<Sprite>(0, 1, 1.0f, 2.0f));
		particleSystem.addParticleModifier(new ColorParticleModifier<Sprite>(0, 0.2f, 1, 1, 0, 0.5f, 0, 0));
		particleSystem.addParticleModifier(new ColorParticleModifier<Sprite>(0.45f, 0.5f, 1, 1, 0.5f, 1, 0, 1));
		particleSystem.addParticleModifier(new AlphaParticleModifier<Sprite>(0, 0.1f, 0, 1));
		particleSystem.addParticleModifier(new AlphaParticleModifier<Sprite>(0.3f, 5, 1, 0));
		particleSystem.addParticleModifier(new ExpireParticleModifier<Sprite>(0.5f));

	    this.attachChild(particleSystem);
	}
	
	
	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		
		timer += pSecondsElapsed;
		
		if(timer > 3){
			model.removeObjectFromScene(this);
		}
		else{
			super.onManagedUpdate(pSecondsElapsed);
		}


	}
	
}