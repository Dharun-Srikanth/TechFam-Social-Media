import { Component, ElementRef, NgZone, OnDestroy, OnInit, ViewChild } from '@angular/core';
import * as THREE from 'three';

import {GLTF, GLTFLoader} from 'three/examples/jsm/loaders/GLTFLoader'

@Component({
  selector: 'app-chats',
  templateUrl: './chats.component.html',
  styleUrls: ['./chats.component.css']
})
export class ChatsComponent {
  chats:Number[] = [1,2,3]


  // @ViewChild('scene', { static: true })
  // private sceneElement!: ElementRef;

  // private scene!: THREE.Scene;
  // private camera!: THREE.PerspectiveCamera;
  // private renderer!: THREE.WebGLRenderer;
  // private mixer!: THREE.AnimationMixer;

  // constructor(private ngZone: NgZone) {}

  // ngOnInit(): void {
  //   this.initScene();
  //   this.loadAnimatedModel();
  //   this.render();
  // }

  // ngOnDestroy(): void {
  //   // Clean up resources when the component is destroyed
  // }

  // private initScene(): void {
  //   // Initialize your 3D scene here
  //   this.scene = new THREE.Scene();

  //   // Create a camera
  //   this.camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000);
  //   this.camera.position.z = 5;

  //   // Create a renderer
  //   this.renderer = new THREE.WebGLRenderer();
  //   this.renderer.setSize(window.innerWidth, window.innerHeight);

  //   // Append the renderer to the DOM
  //   this.sceneElement.nativeElement.appendChild(this.renderer.domElement);

  //   // Create an AnimationMixer
  //   this.mixer = new THREE.AnimationMixer(this.scene);
  // }

  // private loadAnimatedModel(): void {
  //   const loader = new GLTFLoader();

  //   loader.load('../../../assets/3d/scene.gltf', (gltf: GLTF) => {
  //     // Add the loaded model to the scene
  //     const model = gltf.scene;
  //     this.mixer.clipAction(gltf.animations[0]).play();
  //     this.scene.add(model);
  //   });
  // }

  // private render(): void {
  //   this.ngZone.runOutsideAngular(() => {
  //     const animate = () => {
  //       // Add your animation logic here

  //       // Update the mixer on each animation frame
  //       this.mixer.update(0.01);

  //       this.renderer.render(this.scene, this.camera);
  //       requestAnimationFrame(animate);
  //     };

  //     animate();
  //   });
  // }
}
