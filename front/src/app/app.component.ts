import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { InitUserService } from './@theme/services/init-user.service';

@Component({
  selector: 'ngx-app',
  template: '<router-outlet></router-outlet>',
})
export class AppComponent implements OnInit, OnDestroy {

  private destroy$: Subject<void> = new Subject<void>();

  constructor(private initUserService: InitUserService) {
    this.initUser();
  }

  ngOnInit(): void {
  }

  initUser() {
    this.initUserService.initCurrentUser()
      .pipe(
        takeUntil(this.destroy$),
      )
      .subscribe();
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
