
public interface Subject {
	public void RegistRequest(observer o);
	public void RemoveRequest(observer o);
	public void notifyObservers();
}
