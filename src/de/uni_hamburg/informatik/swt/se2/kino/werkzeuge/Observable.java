package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge;

import java.util.HashSet;
import java.util.Set;

/**
 * Abstrakte Klasse observable ermöglicht es...
 * 
 * @author Division durch Null
 * @version 0.01
 */
public abstract class Observable {

	private Set<Observer> _observer;
	
	/**
	 * Konstruktor von Observable
	 */
	public Observable() {
		
		_observer = new HashSet<Observer>();
	}

	/**
	 * Fuegt einen neuen Beobachter zur Menge hinzu.
	 * 
	 * @param observer der zur Menge hinzuzufuegende Beobachter
	 * 
	 * @require observer != null
	 */
	public void addObserver(Observer observer) {
		
		assert observer != null : "Vorbedingung verletzt: null";
		_observer.add(observer);
	}

	/**
	 * Entfernt einen Observer aus der Menge
	 *
	 * @param observer der zu entfernende Observer
	 *
	 * @require observer != null
	 */
	public void removeObserver(Observer observer) {

		assert observer != null : "Vorbedingung verletzt: null";
		_observer.remove(observer);
	}

	/**
	 * Informiert alle Beobachter über eine Aenderung
	 */
	public void notfiyObserver() {
		
		for(Observer obs : _observer) {
			obs.update(this);
		}
	}
}
