package tpost.SimpleTest.util;

import io.netty.util.ReferenceCounted;

public class ByteBufUtils {
	
	
    /**
     * ReferenceCount 체크 후 release 로직 추가
     * 
     * original : io.netty.util.ReferenceCountUtil
     * Try to call {@link ReferenceCounted#release()} if the specified message implements {@link ReferenceCounted}.
     * If the specified message doesn't implement {@link ReferenceCounted}, this method does nothing.
     */
    public static boolean release(Object msg) {
        if (msg instanceof ReferenceCounted) {
        	
        	/** 아래 로직 추가 **/
        	if (((ReferenceCounted) msg).refCnt() > 0 ) {
        		return ((ReferenceCounted) msg).release();
        	}           
        }
        return false;
    }

}
