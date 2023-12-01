import { Component } from '@angular/core';
import { AppResponse } from 'src/app/model/appResponse';
import { Explore } from 'src/app/model/explore';
import { ExploreService } from 'src/app/service/explore.service';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-explore',
  templateUrl: './explore.component.html',
  styleUrls: ['./explore.component.css']
})
export class ExploreComponent {

  constructor(private storageService: StorageService, private exploreService: ExploreService){
    this.getExplorePosts(this.id);
  }

  explore:Explore[] = [];
  id:number = this.storageService.getLoggedInUser().id;

  getExplorePosts(id:number) {
    this.exploreService.viewExplore(id).subscribe({
      next: (response: AppResponse) => {
        this.explore = response.data;
        console.log(this.explore);
        
      },
      error: (err) => console.log(err),
      complete: () => console.log("Explore completed")
      
    });
  }

  getUserDetails() {
    return JSON.parse(localStorage.getItem('details')!);
  }

}
