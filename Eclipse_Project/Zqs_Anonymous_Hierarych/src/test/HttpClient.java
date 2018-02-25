package test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class HttpClient {


	public abstract static class ResponseCallback<T> {
		public Type type;
		public Object tag;

		public ResponseCallback(Object tag) {
			this.tag = tag;
			type = getSuperClassType(getClass());
		}

		public ResponseCallback() {
			this(null);
		}


		Type getSuperClassType(Class<?> subType) {
			Type superType = subType.getGenericSuperclass();
			if (superType instanceof Class) {
				throw new RuntimeException("please check type.");
			}
			ParameterizedType pt = (ParameterizedType) superType;
			return pt.getActualTypeArguments()[0];
		}

		public abstract void onResponse(T response);
	}
}
