package view;

public interface IModel2ViewAdapter {
	public void update();
	
	public static final IModel2ViewAdapter NuLL_OBJECT = new IModel2ViewAdapter() {
		public void update() {}
	};
}
