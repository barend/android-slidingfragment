This is the source code for a [Stack Overflow question][q] I posted.

# Android sliding fragment

This is an implementation of the [sliding fragments][ytsf] DevByte. As this
fragment slides in, it draws a shadow over the underlying content. This is
implemented by rendering the fragment at twice the screen width.

There's an intermittent problem where this shadow is drawn properly as the
fragment slides in, but not as the fragment slides back out. A more detailed
description is in the question on Stack Overflow.

At the time of writing, I don't have access to my physical test devices. I'll
update my observations as those come into grasp. Note that the sample size on
each of the below isn't very large (one or two tries each), so I'm not sure
whether the problem is consistent per device or somehow varies across installs.
I've been unable to automate the observation, so it's tedious going to compile
this list.

<table>
<tr><th>Asus Nexus 7, 1st gen, Android 4.3</th>
<td>exhibits the problem</td></tr>
<tr><th>Emulator, Nexus S, 4.0.3 system image, ARM w/ Host GPU</th>
<td>works as intended</td></tr>
<tr><th>Emulator, Nexus S, 4.0.3 system image, ARM w/o Host GPU</th>
<td>works as intended</td></tr>
<tr><th>Emulator, Galaxy Nexus, fresh 4.1.2 system image, ARM w/ Host GPU</th>
<td>exhibits the problem</td></tr>
<tr><th>Emulator, Galaxy Nexus, fresh 4.1.2 system image, ARM w/o Host GPU</th>
<td>works as intended</td></tr>
<tr><th>Emulator, Galaxy Nexus, fresh 4.2.2 system image, ARM w/ Host GPU</th>
<td>exhibits the problem</td></tr>
<tr><th>Emulator, Galaxy Nexus, fresh 4.2.2 system image, ARM w/o Host GPU</th>
<td>works as intended</td></tr>
<tr><th>Emulator, Galaxy Nexus, fresh 4.3 system image, ARM w/ Host GPU</th>
<td>exhibits the problem</td></tr>
<tr><th>Emulator, Galaxy Nexus, fresh 4.3 system image, ARM w/o Host GPU</th>
<td>works as intended</td></tr>
<tr><th>Emulator, Galaxy Nexus, fresh 4.3 system image, x86 w/ Intel HAXM</th>
<td>works as intended</td></tr>
<tr><th>Emulator, Nexus 4, fresh 4.4 system image, ARM w/ Host GPU</th>
<td>exhibits the problem</td></tr>
<tr><th>Emulator, Nexus 4, fresh 4.4 system image, ARM w/o Host GPU</th>
<td>works as intended</td></tr>
</table>

It's clear that the Host GPU acceleration surfaces the problem on the emulators
running Jelly Bean or newer. I'd leave it at that if my Nexus 7 were showing
the animation as intended, but alas, it's not. It looks like I'm tripping over
an optimization in Project Butter.


[ytsf]: http://www.youtube.com/watch?v=xbl5cxfA1n4
[q]: http://stackoverflow.com/q/19742350/49489
